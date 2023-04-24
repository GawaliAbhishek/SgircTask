const path = require("path");
const fs = require("fs");
const express = require("express");
const Register = require("../register");
const cloudinary = require("cloudinary").v2;
const fileUpload = require("express-fileupload");
const router = new express.Router();

const folderPath = path.join(__dirname,'../../tmp');
//console.log(folderPath);
cloudinary.config({
  cloud_name: "dktwbfcrs",
  api_key: "596455185414514",
  api_secret: "RpfDtOYBYuAdhSBRFs_6jZcMDnI",
});

router.use(
  fileUpload({
    useTempFiles: true,
  })
);

router.post("/", async (req, res) => {
  try {
    const file = req.files.image;
    var url;
    await cloudinary.uploader.upload(file.tempFilePath, (err, result) => {
      url = result.url;
      res.send(url);
    });
    const gettingData = new Register({
      imgUrl: url,
    });
    const userData = await gettingData.save();
    console.log(userData);
    res.status(200);

    // Delete the folder and its contents
      fs.rm(folderPath, { recursive: true }, (err) => {
        if (err) {
          console.error(`Error deleting folder: ${err}`);
        } else {
          console.log(`Folder deleted: ${folderPath}`);
        }
      });
  } catch (err) {
    res.status(400);
  }
});

module.exports = router;
