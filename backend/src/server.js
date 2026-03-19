const express = require('express')
const app = express()
const port = process.env.PORT || 3000

const db = require('./config/db');
const rootes = require('./routes/index');

app.use('/api', rootes);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})