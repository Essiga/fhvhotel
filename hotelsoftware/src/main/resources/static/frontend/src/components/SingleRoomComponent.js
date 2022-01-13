import React from 'react';
import singleRoom from "../images/singleRoom.jpg"


class SingleRoomComponent extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div className="grid grid-cols-2 gap-4 mt-5">
                   <div>
                       <img src={singleRoom} className=""/>
                   </div>
                   <div>
                       <div className="text-2xl font-bold">Single Room</div>
                       <div className="text-xl">Stylish timeless furnishings with warm colors - on 18 square meters.
                           Simple room in pastel tones with a view of the local mountain</div>

                       <div className="mt-5 text-xl font-bold">Room details:</div>

                       <ul className="list-disc ml-8">
                           <li className="text-xl">balcony</li>
                           <li className="text-xl">bathtub / WC</li>
                           <li className="text-xl">flat screen tv</li>
                           <li className="text-xl">WLAN</li>
                       </ul>

                   </div>

                </div>
            </React.Fragment>
        );
    }

}
export default SingleRoomComponent;