"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _BookingDataDTO = _interopRequireDefault(require("../model/BookingDataDTO"));

var _BookingForm = _interopRequireDefault(require("../model/BookingForm"));

var _BookingId = _interopRequireDefault(require("../model/BookingId"));

var _GuestDTO = _interopRequireDefault(require("../model/GuestDTO"));

var _GuestId = _interopRequireDefault(require("../model/GuestId"));

var _RoomPriceDTO = _interopRequireDefault(require("../model/RoomPriceDTO"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }

/**
* BookingRestController service.
* @module api/BookingRestControllerApi
* @version v0
*/
var BookingRestControllerApi = /*#__PURE__*/function () {
  /**
  * Constructs a new BookingRestControllerApi. 
  * @alias module:api/BookingRestControllerApi
  * @class
  * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
  * default to {@link module:ApiClient#instance} if unspecified.
  */
  function BookingRestControllerApi(apiClient) {
    _classCallCheck(this, BookingRestControllerApi);

    this.apiClient = apiClient || _ApiClient.default.instance;
  }
  /**
   * Callback function to receive the result of the createBooking operation.
   * @callback module:api/BookingRestControllerApi~createBookingCallback
   * @param {String} error Error message, if any.
   * @param {module:model/BookingId} data The data returned by the service call.
   * @param {String} response The complete HTTP response.
   */

  /**
   * @param {module:model/BookingDataDTO} bookingDataDTO 
   * @param {module:api/BookingRestControllerApi~createBookingCallback} callback The callback function, accepting three arguments: error, data, response
   * data is of type: {@link module:model/BookingId}
   */


  _createClass(BookingRestControllerApi, [{
    key: "createBooking",
    value: function createBooking(bookingDataDTO, callback) {
      var postBody = bookingDataDTO; // verify the required parameter 'bookingDataDTO' is set

      if (bookingDataDTO === undefined || bookingDataDTO === null) {
        throw new Error("Missing the required parameter 'bookingDataDTO' when calling createBooking");
      }

      var pathParams = {};
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = [];
      var contentTypes = ['application/json'];
      var accepts = ['*/*'];
      var returnType = _BookingId.default;
      return this.apiClient.callApi('/rest/booking/createBooking', 'POST', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null, callback);
    }
    /**
     * Callback function to receive the result of the createGuest operation.
     * @callback module:api/BookingRestControllerApi~createGuestCallback
     * @param {String} error Error message, if any.
     * @param {module:model/GuestId} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {module:model/GuestDTO} guestDTO 
     * @param {module:api/BookingRestControllerApi~createGuestCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link module:model/GuestId}
     */

  }, {
    key: "createGuest",
    value: function createGuest(guestDTO, callback) {
      var postBody = guestDTO; // verify the required parameter 'guestDTO' is set

      if (guestDTO === undefined || guestDTO === null) {
        throw new Error("Missing the required parameter 'guestDTO' when calling createGuest");
      }

      var pathParams = {};
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = [];
      var contentTypes = ['application/json'];
      var accepts = ['*/*'];
      var returnType = _GuestId.default;
      return this.apiClient.callApi('/rest/booking/createGuest', 'POST', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null, callback);
    }
    /**
     * Callback function to receive the result of the getRoomPrices operation.
     * @callback module:api/BookingRestControllerApi~getRoomPricesCallback
     * @param {String} error Error message, if any.
     * @param {module:model/RoomPriceDTO} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {module:api/BookingRestControllerApi~getRoomPricesCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link module:model/RoomPriceDTO}
     */

  }, {
    key: "getRoomPrices",
    value: function getRoomPrices(callback) {
      var postBody = null;
      var pathParams = {};
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = [];
      var contentTypes = [];
      var accepts = ['*/*'];
      var returnType = _RoomPriceDTO.default;
      return this.apiClient.callApi('/rest/booking/getRoomPrices', 'GET', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null, callback);
    }
    /**
     * Callback function to receive the result of the getTotalRoom operation.
     * @callback module:api/BookingRestControllerApi~getTotalRoomCallback
     * @param {String} error Error message, if any.
     * @param {Array.<Number>} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {module:model/BookingForm} bookingForm 
     * @param {module:api/BookingRestControllerApi~getTotalRoomCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Array.<Number>}
     */

  }, {
    key: "getTotalRoom",
    value: function getTotalRoom(bookingForm, callback) {
      var postBody = bookingForm; // verify the required parameter 'bookingForm' is set

      if (bookingForm === undefined || bookingForm === null) {
        throw new Error("Missing the required parameter 'bookingForm' when calling getTotalRoom");
      }

      var pathParams = {};
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = [];
      var contentTypes = ['application/json'];
      var accepts = ['*/*'];
      var returnType = ['Number'];
      return this.apiClient.callApi('/rest/booking/getTotalRoom', 'POST', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null, callback);
    }
  }]);

  return BookingRestControllerApi;
}();

exports.default = BookingRestControllerApi;