'use strict';

module.exports = function(Sinister) {
  Sinister.updateState = async function (newState, sinisterId) {
    let sinister = await Sinister.findById(sinisterId);
    if(sinister){
      await sinister.updateAttribute("state", newState);
    }
  };

  Sinister.remoteMethod('updateState', {
    accepts: [{arg: 'newState', type: 'number', required: true}, {arg: 'sinisterId', type: 'number', required: true}],
    http: {path: '/updateState', verb: 'post'}
  });

  Sinister.updateSeverity = async function (newSeverity, sinisterId){
    let sinister = await Sinister.findById(sinisterId);
    if(sinister){
      await sinister.updateAttribute("severity", newSeverity);
    }
  };

  Sinister.remoteMethod('updateSeverity', {
    accepts: [{arg: 'newSeverity', type: 'number', required: true}, {arg: 'sinisterId', type: 'number', required: true}],
    http: {path: '/updateSeverity', verb: 'post'}
  })
};
