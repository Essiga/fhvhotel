import React from 'react';
import superiorRoom from "../images/superiorRoom.jpg"


class SingleRoomComponent extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div className="grid grid-cols-2 gap-4 mt-5">
                   <div>
                       <img src={superiorRoom} className=""/>
                   </div>
                   <div>
                       <div className="text-2xl font-bold">Superior Room</div>
                       <div className="text-xl">
                           Room with space to dream - on 30 square meters.
                           All Superior Rooms allow a view of the entire Lech mountain panorama.</div>

                       <div className="mt-5 text-xl font-bold">Room details:</div>

                       <ul className="list-disc ml-8">
                           <li className="text-xl">balcony</li>
                           <li className="text-xl">bathtub / WC</li>
                           <li className="text-xl">flat screen tv</li>
                           <li className="text-xl">WLAN</li>
                           <li className="text-xl">hairdryer and bath basket</li>
                           <li className="text-xl">Safe</li>
                       </ul>

                   </div>

                </div>
            </React.Fragment>
        );
    }

}
export default SingleRoomComponent;