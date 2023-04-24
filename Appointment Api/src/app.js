const express = require("express");
const app = express();
require("./db/connection");
const bodyParser = require("body-parser");
const Register = require("./models/register");
const Router = require("./models/router/routers");
const port = process.env.PORT || 8082;
const cors=require('cors')

app.use(express.json());
app.use(cors('http://localhost:3000'))
app.use(Router);
app.use(express.urlencoded({ extended: false }));


app.listen(port, () => {
  console.log(`Listening port ${port}`);
});
