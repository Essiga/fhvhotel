"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _GuestId = _interopRequireDefault(require("./GuestId"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }

/**
 * The BookingDataDTO model module.
 * @module model/BookingDataDTO
 * @version v0
 */
var BookingDataDTO = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>BookingDataDTO</code>.
   * @alias module:model/BookingDataDTO
   */
  function BookingDataDTO() {
    _classCallCheck(this, BookingDataDTO);

    BookingDataDTO.initialize(this);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(BookingDataDTO, null, [{
    key: "initialize",
    value: function initialize(obj) {}
    /**
     * Constructs a <code>BookingDataDTO</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/BookingDataDTO} obj Optional instance to populate.
     * @return {module:model/BookingDataDTO} The populated <code>BookingDataDTO</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new BookingDataDTO();

        if (data.hasOwnProperty('guestId')) {
          obj['guestId'] = _GuestId.default.constructFromObject(data['guestId']);
        }

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
      }

      return obj;
    }
  }]);

  return BookingDataDTO;
}();
/**
 * @member {module:model/GuestId} guestId
 */


BookingDataDTO.prototype['guestId'] = undefined;
/**
 * @member {Number} singleRoomCount
 */

BookingDataDTO.prototype['singleRoomCount'] = undefined;
/**
 * @member {Number} doubleRoomCount
 */

BookingDataDTO.prototype['doubleRoomCount'] = undefined;
/**
 * @member {Number} superiorRoomCount
 */

BookingDataDTO.prototype['superiorRoomCount'] = undefined;
/**
 * @member {String} checkInDate
 */

BookingDataDTO.prototype['checkInDate'] = undefined;
/**
 * @member {String} checkOutDate
 */

BookingDataDTO.prototype['checkOutDate'] = undefined;
var _default = BookingDataDTO;
exports.default = _default;