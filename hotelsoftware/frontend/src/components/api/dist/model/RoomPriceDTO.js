"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }

/**
 * The RoomPriceDTO model module.
 * @module model/RoomPriceDTO
 * @version v0
 */
var RoomPriceDTO = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>RoomPriceDTO</code>.
   * @alias module:model/RoomPriceDTO
   */
  function RoomPriceDTO() {
    _classCallCheck(this, RoomPriceDTO);

    RoomPriceDTO.initialize(this);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(RoomPriceDTO, null, [{
    key: "initialize",
    value: function initialize(obj) {}
    /**
     * Constructs a <code>RoomPriceDTO</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/RoomPriceDTO} obj Optional instance to populate.
     * @return {module:model/RoomPriceDTO} The populated <code>RoomPriceDTO</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new RoomPriceDTO();

        if (data.hasOwnProperty('singleRoomPrice')) {
          obj['singleRoomPrice'] = _ApiClient.default.convertToType(data['singleRoomPrice'], 'Number');
        }

        if (data.hasOwnProperty('doubleRoomPrice')) {
          obj['doubleRoomPrice'] = _ApiClient.default.convertToType(data['doubleRoomPrice'], 'Number');
        }

        if (data.hasOwnProperty('superiorRoomPrice')) {
          obj['superiorRoomPrice'] = _ApiClient.default.convertToType(data['superiorRoomPrice'], 'Number');
        }
      }

      return obj;
    }
  }]);

  return RoomPriceDTO;
}();
/**
 * @member {Number} singleRoomPrice
 */


RoomPriceDTO.prototype['singleRoomPrice'] = undefined;
/**
 * @member {Number} doubleRoomPrice
 */

RoomPriceDTO.prototype['doubleRoomPrice'] = undefined;
/**
 * @member {Number} superiorRoomPrice
 */

RoomPriceDTO.prototype['superiorRoomPrice'] = undefined;
var _default = RoomPriceDTO;
exports.default = _default;