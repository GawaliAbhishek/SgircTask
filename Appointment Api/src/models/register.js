const mongoose = require("mongoose");
const express = require("express");

const registerSchema = mongoose.Schema({
  fname: {
    type: String,
    require: true,
  },
  lname: {
    type: String,
    require: true,
  },
  phone: {
    type: Number,
    default:9307243841,
    require: true,
  },
  docname: {
    type: String,
    require: true,
  },
  age: {
    type: Number,
    require: true,
  },
  date: {
    type: String,
    require: true,
  },
  time: {
    type: String,
    require: true,
  },
});

const Register = new mongoose.model("AppointmentForm", registerSchema);

module.exports = Register;
