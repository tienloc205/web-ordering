const getHello = (req, res) => {
  res.send('Hello World!');
  console.log('Request has been sent and received at /api/test');
}

module.exports = {
  getHello
}