'use strict';
const loopb = require('loopback');

module.exports = function(Account) {
  Account.updateRank = async function (newRank, accountId) {
    let user = await Account.findById(accountId);
    if(user){
      await user.updateAttribute("rank", newRank);
    }
  };

  Account.remoteMethod('updateRank', {
    accepts: [{arg: 'newRank', type: 'number', required: true}, {arg: 'accountId', type: 'string', required: true}],
    http: {path: '/updateRank', verb: 'post'}
  });

  Account.findAllMatches = async function (longitude, latitude){
    return await Account.find({
      where: {
        location: {
          near: new loopb.GeoPoint({ lat: latitude, lng: longitude}),
          maxDistance: 10,
          unit: 'kilometers'
        }
      }
    });
  };

  Account.remoteMethod('findAllMatches', {
    accepts: [{arg: 'longitude', type: 'number', required: true}, {arg: 'latitude', type: 'number', required: true}],
    returns: {arg: 'location', type: 'string'},
    http: {path: '/findAllMatches', verb: 'post'}
  });
};
