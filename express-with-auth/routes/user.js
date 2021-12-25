const router = require("express").Router();
const bc = require("bcryptjs");
const jwt = require("jsonwebtoken");
const { User } = require("../models/User");

router.post("/register", async (req, res) => {
  var phone = req.body.phone;
  var name = req.body.name;
  var password = req.body.password;
  var email = req.body.email;
  let user = await User.findOne({ phone });
  if (phone.length < 10)
    return res
      .status(400)
      .json({ status: 1, mssg: "Please enter a valid phone number" });
  if (password.length < 6)
    return res.status(400).json({
      status: 1,
      mssg: "Password length should me more than 6 characters",
    });
  if (name.length < 1)
    return res.status(400).json({
      status: 1,
      mssg: "A name is required!",
    });
  if (user)
    return res.status(400).json({
      status: 1,
      mssg: "User has already been registered, Please use different Phone Number",
    });

  const salt = await bc.genSalt(10);
  const hashed = await bc.hash(req.body.password, salt);
  user = new User({
    name: req.body.name,
    email: req.body.email,
    phone: req.body.phone,
    password: hashed,
    sex: req.body.sex,
  });
  const newUser = await user.save();
  const token = jwt.sign({ _id: newUser._id }, process.env.jwt_secret);
  res.json({ user: newUser, token });
});

router.post("/login", async (req, res) => {
  const { phone, password } = req.body;
  const userExist = await User.findOne({ phone: phone });
  if (!userExist)
    return res.status(400).json({ status: 1, mssg: "User does not exists" });
  const validPassword = await bc.compare(password, userExist.password);
  if (!validPassword)
    return res
      .status(500)
      .json({ status: 1, mssg: "Wrong Password, please try again" });
  const token = jwt.sign({ _id: userExist._id }, process.env.jwt_secret);
  return res.json({ token, user: userExist });
});

router.post("/update", async (req, res) => {
  const { phone, password, name, email } = req.body;
  const userExist = await User.findOne({ phone: phone });
  if (!userExist)
    return res.status(400).json({ status: 1, mssg: "User does not exists" });
  const salt = await bc.genSalt(10);
  const hashed = await bc.hash(password, salt);
  const user = await User.findOneAndUpdate(
    { phone: phone },
    {
      name,
      email,
      phone,
      password: hashed,
    },
    { new: true }
  );
  return res.json({ user });
});

module.exports = router;
