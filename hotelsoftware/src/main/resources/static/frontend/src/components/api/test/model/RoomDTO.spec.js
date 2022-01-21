/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 *
 */

(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD.
    define(['expect.js', process.cwd()+'/src/index'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    factory(require('expect.js'), require(process.cwd()+'/src/index'));
  } else {
    // Browser globals (root is window)
    factory(root.expect, root.OpenApiDefinition);
  }
}(this, function(expect, OpenApiDefinition) {
  'use strict';

  var instance;

  beforeEach(function() {
    instance = new OpenApiDefinition.RoomDTO();
  });

  var getProperty = function(object, getter, property) {
    // Use getter method if present; otherwise, get the property directly.
    if (typeof object[getter] === 'function')
      return object[getter]();
    else
      return object[property];
  }

  var setProperty = function(object, setter, property, value) {
    // Use setter method if present; otherwise, set the property directly.
    if (typeof object[setter] === 'function')
      object[setter](value);
    else
      object[property] = value;
  }

  describe('RoomDTO', function() {
    it('should create an instance of RoomDTO', function() {
      // uncomment below and update the code to test RoomDTO
      //var instance = new OpenApiDefinition.RoomDTO();
      //expect(instance).to.be.a(OpenApiDefinition.RoomDTO);
    });

    it('should have the property id (base name: "id")', function() {
      // uncomment below and update the code to test the property id
      //var instance = new OpenApiDefinition.RoomDTO();
      //expect(instance).to.be();
    });

    it('should have the property roomCategory (base name: "roomCategory")', function() {
      // uncomment below and update the code to test the property roomCategory
      //var instance = new OpenApiDefinition.RoomDTO();
      //expect(instance).to.be();
    });

    it('should have the property roomNumber (base name: "roomNumber")', function() {
      // uncomment below and update the code to test the property roomNumber
      //var instance = new OpenApiDefinition.RoomDTO();
      //expect(instance).to.be();
    });

    it('should have the property roomStatus (base name: "roomStatus")', function() {
      // uncomment below and update the code to test the property roomStatus
      //var instance = new OpenApiDefinition.RoomDTO();
      //expect(instance).to.be();
    });

    it('should have the property bookingId (base name: "bookingId")', function() {
      // uncomment below and update the code to test the property bookingId
      //var instance = new OpenApiDefinition.RoomDTO();
      //expect(instance).to.be();
    });

  });

}));
