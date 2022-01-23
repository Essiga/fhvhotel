"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _BookingId = _interopRequireDefault(require("./BookingId"));

var _RoomDTO = _interopRequireDefault(require("./RoomDTO"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }

/**
 * The BookingForm model module.
 * @module model/BookingForm
 * @version v0
 */
var BookingForm = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>BookingForm</code>.
   * @alias module:model/BookingForm
   */
  function BookingForm() {
    _classCallCheck(this, BookingForm);

    BookingForm.initialize(this);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(BookingForm, null, [{
    key: "initialize",
    value: function initialize(obj) {}
    /**
     * Constructs a <code>BookingForm</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/BookingForm} obj Optional instance to populate.
     * @return {module:model/BookingForm} The populated <code>BookingForm</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new BookingForm();

        if (data.hasOwnProperty('singleRoomCount')) {
          obj['singleRoomCount'] = _ApiClient.default.convertToType(data['singleRoomCount'], 'Number');
        }

        if (data.hasOwnProperty('doubleRoomCount')) {
          obj['doubleRoomCount'] = _ApiClient.default.convertToType(data['doubleRoomCount'], 'Number');
        }

        if (data.hasOwnProperty('superiorRoomCount')) {
          obj['superiorRoomCount'] = _ApiClient.default.convertToType(data['superiorRoomCount'], 'Number');
        }

        if (data.hasOwnProperty('checkInDate')) {
          obj['checkInDate'] = _ApiClient.default.convertToType(data['checkInDate'], 'String');
        }

        if (data.hasOwnProperty('checkOutDate')) {
          obj['checkOutDate'] = _ApiClient.default.convertToType(data['checkOutDate'], 'String');
        }

        if (data.hasOwnProperty('voucherCode')) {
          obj['voucherCode'] = _ApiClient.default.convertToType(data['voucherCode'], 'String');
        }

        if (data.hasOwnProperty('bookingId')) {
          obj['bookingId'] = _BookingId.default.constructFromObject(data['bookingId']);
        }

        if (data.hasOwnProperty('roomList')) {
          obj['roomList'] = _ApiClient.default.convertToType(data['roomList'], [_RoomDTO.default]);
        }

        if (data.hasOwnProperty('validDuration')) {
          obj['validDuration'] = _ApiClient.default.convertToType(data['validDuration'], 'Boolean');
        }

        if (data.hasOwnProperty('validCategoryCount')) {
          obj['validCategoryCount'] = _ApiClient.default.convertToType(data['validCategoryCount'], 'Boolean');
        }
      }

      return obj;
    }
  }]);

  return BookingForm;
}();
/**
 * @member {Number} singleRoomCount
 */


BookingForm.prototype['singleRoomCount'] = undefined;
/**
 * @member {Number} doubleRoomCount
 */

BookingForm.prototype['doubleRoomCount'] = undefined;
/**
 * @member {Number} superiorRoomCount
 */

BookingForm.prototype['superiorRoomCount'] = undefined;
/**
 * @member {String} checkInDate
 */

BookingForm.prototype['checkInDate'] = undefined;
/**
 * @member {String} checkOutDate
 */

BookingForm.prototype['checkOutDate'] = undefined;
/**
 * @member {String} voucherCode
 */

BookingForm.prototype['voucherCode'] = undefined;
/**
 * @member {module:model/BookingId} bookingId
 */

BookingForm.prototype['bookingId'] = undefined;
/**
 * @member {Array.<module:model/RoomDTO>} roomList
 */

BookingForm.prototype['roomList'] = undefined;
/**
 * @member {Boolean} validDuration
 */

BookingForm.prototype['validDuration'] = undefined;
/**
 * @member {Boolean} validCategoryCount
 */

BookingForm.prototype['validCategoryCount'] = undefined;
var _default = BookingForm;
exports.default = _default;