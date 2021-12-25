const mongoose = require("mongoose");
const { Schema } = require("mongoose");

const userSchema = new Schema({
  email: { type: String, required: true },
  name: String,
  password: { type: String, required: true },
  phone: String,
});

//userSchema.plugin(require("mongoose-autopopulate"));
const User = mongoose.model("user", userSchema);

module.exports = { User };
