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

import ApiClient from '../ApiClient';
import BookingId from './BookingId';

/**
 * The RoomDTO model module.
 * @module model/RoomDTO
 * @version v0
 */
class RoomDTO {
    /**
     * Constructs a new <code>RoomDTO</code>.
     * @alias module:model/RoomDTO
     */
    constructor() { 
        
        RoomDTO.initialize(this);
    }

    /**
     * Initializes the fields of this object.
     * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
     * Only for internal use.
     */
    static initialize(obj) { 
    }

    /**
     * Constructs a <code>RoomDTO</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/RoomDTO} obj Optional instance to populate.
     * @return {module:model/RoomDTO} The populated <code>RoomDTO</code> instance.
     */
    static constructFromObject(data, obj) {
        if (data) {
            obj = obj || new RoomDTO();

            if (data.hasOwnProperty('id')) {
                obj['id'] = ApiClient.convertToType(data['id'], 'Number');
            }
            if (data.hasOwnProperty('roomCategory')) {
                obj['roomCategory'] = ApiClient.convertToType(data['roomCategory'], 'String');
            }
            if (data.hasOwnProperty('roomNumber')) {
                obj['roomNumber'] = ApiClient.convertToType(data['roomNumber'], 'Number');
            }
            if (data.hasOwnProperty('roomStatus')) {
                obj['roomStatus'] = ApiClient.convertToType(data['roomStatus'], 'String');
            }
            if (data.hasOwnProperty('bookingId')) {
                obj['bookingId'] = BookingId.constructFromObject(data['bookingId']);
            }
        }
        return obj;
    }


}

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



export default RoomDTO;

