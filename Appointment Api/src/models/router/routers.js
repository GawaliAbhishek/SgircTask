const express = require("express");
const Register = require("../register");
const router = new express.Router();

router.post("/", async (req, res) => {
  try {
    const addingData = await new Register(req.body);
    const insertedData = await addingData.save();
    console.log(insertedData);
    res.status(201).send(insertedData);
  } catch (err) {
    res.status(400).send(err);
  }
});

router.get("/", async (req, res) => {
  try {
    const getData = await Register.find();
    console.log(getData.date);
    res.status(200).send(getData);
  } catch (err) {
    res.status(400).send(err);
  }
});

//get data individually
router.get("/:fname", async (req, res) => {
  try {
    const _name = req.params.fname;
    const getData = await Register.find({ fname: _name });
    console.log(getData);
    res.status(200).send(getData);
  } catch (err) {
    res.status(400).send(err);
  }
});


// Notification sending
const accountSid = "ACcc9e747b79eb5c6e80162c2b093817a6";
const authToken = "4b2a8553cf0783c1147d44bacc5cbb61";
const client = require("twilio")(accountSid, authToken);

router.post("/notification/:fname/:docname/:mobileNo", async (req, res) => {
  try {
    const _fname = req.params.fname;
    const _docname = req.params.docname;
    const _number = req.params.mobileNo;
    const _message = `${_fname} your appointment with Doctor ${_docname} is scheduled today`;
    client.messages
      .create({
        body: _message,
        from: "+16073884939",
        to: `+91${_number}`,
      })
      .then((message) => res.send(message.sid));
  } catch (err) {
    res.status(400).send(err);
  }
});

module.exports = router;
