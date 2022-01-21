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
 * The GuestDTO model module.
 * @module model/GuestDTO
 * @version v0
 */
var GuestDTO = /*#__PURE__*/function () {
  /**
   * Constructs a new <code>GuestDTO</code>.
   * @alias module:model/GuestDTO
   */
  function GuestDTO() {
    _classCallCheck(this, GuestDTO);

    GuestDTO.initialize(this);
  }
  /**
   * Initializes the fields of this object.
   * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
   * Only for internal use.
   */


  _createClass(GuestDTO, null, [{
    key: "initialize",
    value: function initialize(obj) {}
    /**
     * Constructs a <code>GuestDTO</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/GuestDTO} obj Optional instance to populate.
     * @return {module:model/GuestDTO} The populated <code>GuestDTO</code> instance.
     */

  }, {
    key: "constructFromObject",
    value: function constructFromObject(data, obj) {
      if (data) {
        obj = obj || new GuestDTO();

        if (data.hasOwnProperty('id')) {
          obj['id'] = _ApiClient.default.convertToType(data['id'], 'Number');
        }

        if (data.hasOwnProperty('guestId')) {
          obj['guestId'] = _GuestId.default.constructFromObject(data['guestId']);
        }

        if (data.hasOwnProperty('firstName')) {
          obj['firstName'] = _ApiClient.default.convertToType(data['firstName'], 'String');
        }

        if (data.hasOwnProperty('lastName')) {
          obj['lastName'] = _ApiClient.default.convertToType(data['lastName'], 'String');
        }

        if (data.hasOwnProperty('streetAddress')) {
          obj['streetAddress'] = _ApiClient.default.convertToType(data['streetAddress'], 'String');
        }

        if (data.hasOwnProperty('zip')) {
          obj['zip'] = _ApiClient.default.convertToType(data['zip'], 'String');
        }

        if (data.hasOwnProperty('city')) {
          obj['city'] = _ApiClient.default.convertToType(data['city'], 'String');
        }

        if (data.hasOwnProperty('country')) {
          obj['country'] = _ApiClient.default.convertToType(data['country'], 'String');
        }

        if (data.hasOwnProperty('phoneNumber')) {
          obj['phoneNumber'] = _ApiClient.default.convertToType(data['phoneNumber'], 'String');
        }

        if (data.hasOwnProperty('email')) {
          obj['email'] = _ApiClient.default.convertToType(data['email'], 'String');
        }

        if (data.hasOwnProperty('guestType')) {
          obj['guestType'] = _ApiClient.default.convertToType(data['guestType'], 'String');
        }

        if (data.hasOwnProperty('companyName')) {
          obj['companyName'] = _ApiClient.default.convertToType(data['companyName'], 'String');
        }

        if (data.hasOwnProperty('agencyName')) {
          obj['agencyName'] = _ApiClient.default.convertToType(data['agencyName'], 'String');
        }
      }

      return obj;
    }
  }]);

  return GuestDTO;
}();
/**
 * @member {Number} id
 */


GuestDTO.prototype['id'] = undefined;
/**
 * @member {module:model/GuestId} guestId
 */

GuestDTO.prototype['guestId'] = undefined;
/**
 * @member {String} firstName
 */

GuestDTO.prototype['firstName'] = undefined;
/**
 * @member {String} lastName
 */

GuestDTO.prototype['lastName'] = undefined;
/**
 * @member {String} streetAddress
 */

GuestDTO.prototype['streetAddress'] = undefined;
/**
 * @member {String} zip
 */

GuestDTO.prototype['zip'] = undefined;
/**
 * @member {String} city
 */

GuestDTO.prototype['city'] = undefined;
/**
 * @member {String} country
 */

GuestDTO.prototype['country'] = undefined;
/**
 * @member {String} phoneNumber
 */

GuestDTO.prototype['phoneNumber'] = undefined;
/**
 * @member {String} email
 */

GuestDTO.prototype['email'] = undefined;
/**
 * @member {module:model/GuestDTO.GuestTypeEnum} guestType
 */

GuestDTO.prototype['guestType'] = undefined;
/**
 * @member {String} companyName
 */

GuestDTO.prototype['companyName'] = undefined;
/**
 * @member {String} agencyName
 */

GuestDTO.prototype['agencyName'] = undefined;
/**
 * Allowed values for the <code>guestType</code> property.
 * @enum {String}
 * @readonly
 */

GuestDTO['GuestTypeEnum'] = {
  /**
   * value: "COMPANY"
   * @const
   */
  "COMPANY": "COMPANY",

  /**
   * value: "TRAVEL_AGENCY"
   * @const
   */
  "TRAVEL_AGENCY": "TRAVEL_AGENCY",

  /**
   * value: "REGULAR"
   * @const
   */
  "REGULAR": "REGULAR"
};
var _default = GuestDTO;
exports.default = _default;