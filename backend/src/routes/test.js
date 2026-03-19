const express = require('express')
const router = express.Router();
const {getHello} = require('../controllers/test');

router.get('/', getHello);


module.exports = router;