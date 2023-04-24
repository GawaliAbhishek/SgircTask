const mongoose = require("mongoose");

mongoose
  .connect("mongodb+srv://abhijeet:abhijeet123@cluster0.9su7heh.mongodb.net/AppoinmentData?retryWrites=true&w=majority")
  .then(() => console.log("connected to db"))
  .catch((err) => console.log(err));
