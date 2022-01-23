"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

var _BookingId = _interopRequireDefault(require("./BookingId"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }

/**
 * The RoomDTO model module.
 * @module model/RoomDTO
 * @version v0
 */
var RoomDTO = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>RoomDTO</code>.
   * @alias module:model/RoomDTO
   */
  function RoomDTO() {
    _classCallCheck(this, RoomDTO);

    RoomDTO.initialize(this);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(RoomDTO, null, [{
    key: "initialize",
    value: function initialize(obj) {}
    /**
     * Constructs a <code>RoomDTO</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/RoomDTO} obj Optional instance to populate.
     * @return {module:model/RoomDTO} The populated <code>RoomDTO</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new RoomDTO();

        if (data.hasOwnProperty('id')) {
          obj['id'] = _ApiClient.default.convertToType(data['id'], 'Number');
        }

        if (data.hasOwnProperty('roomCategory')) {
          obj['roomCategory'] = _ApiClient.default.convertToType(data['roomCategory'], 'String');
        }

        if (data.hasOwnProperty('roomNumber')) {
          obj['roomNumber'] = _ApiClient.default.convertToType(data['roomNumber'], 'Number');
        }

        if (data.hasOwnProperty('roomStatus')) {
          obj['roomStatus'] = _ApiClient.default.convertToType(data['roomStatus'], 'String');
        }

        if (data.hasOwnProperty('bookingId')) {
          obj['bookingId'] = _BookingId.default.constructFromObject(data['bookingId']);
        }
      }

      return obj;
    }
  }]);

  return RoomDTO;
}();
/**
 * @member {Number} id
 */


RoomDTO.prototype['id'] = undefined;
/**
 * @member {module:model/RoomDTO.RoomCategoryEnum} roomCategory
 */

RoomDTO.prototype['roomCategory'] = undefined;
/**
 * @member {Number} roomNumber
 */

RoomDTO.prototype['roomNumber'] = undefined;
/**
 * @member {module:model/RoomDTO.RoomStatusEnum} roomStatus
 */

RoomDTO.prototype['roomStatus'] = undefined;
/**
 * @member {module:model/BookingId} bookingId
 */

RoomDTO.prototype['bookingId'] = undefined;
/**
 * Allowed values for the <code>roomCategory</code> property.
 * @enum {String}
 * @readonly
 */

RoomDTO['RoomCategoryEnum'] = {
  /**
   * value: "SINGLE"
   * @const
   */
  "SINGLE": "SINGLE",

  /**
   * value: "DOUBLE"
   * @const
   */
  "DOUBLE": "DOUBLE",

  /**
   * value: "SUPERIOR"
   * @const
   */
  "SUPERIOR": "SUPERIOR"
};
/**
 * Allowed values for the <code>roomStatus</code> property.
 * @enum {String}
 * @readonly
 */

RoomDTO['RoomStatusEnum'] = {
  /**
   * value: "FREE"
   * @const
   */
  "FREE": "FREE",

  /**
   * value: "OCCUPIED"
   * @const
   */
  "OCCUPIED": "OCCUPIED",

  /**
   * value: "CLEANING"
   * @const
   */
  "CLEANING": "CLEANING"
};
var _default = RoomDTO;
exports.default = _default;