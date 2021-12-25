const express = require("express");
const mongoose = require("mongoose");
const app = express();

const dotenv = require("dotenv");
dotenv.config();

app.use(express.json());

const db = process.env.MONGO_URI;

const connect = mongoose
  .connect(db, { useFindAndModify: false })
  .then(() => console.log("Mondo db connected...."))
  .catch((err) => console.log(err));

app.use("/api/user", require("./routes/user"));

const port = process.env.PORT || 5000;

app.listen(port, function () {
  console.log("listening on 3000");
});
