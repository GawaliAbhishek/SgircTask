const mongoose = require("mongoose");

const registerSchema = mongoose.Schema({
imgUrl:{
    type: String,
    require:true
  }
});

const Register = new mongoose.model("UrlDb", registerSchema);

module.exports = Register;
