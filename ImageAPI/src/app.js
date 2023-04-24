const { log, error } = require("console");
const express = require("express");
const app = express();
require("./db/connection");
const bodyParser = require("body-parser");
const Register = require("./models/register");
const Router = require("./models/router/routers");
const cors=require('cors')
const port = process.env.PORT || 8084;

app.use(cors('http://localhost:3000'));
app.use(Router);
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.listen(port, () => {
  console.log(`Listening to ${port}`);
});
