import React from 'react'
import PropTypes from 'prop-types'
import LocationPicker from 'react-location-picker';

import '../AsyncFlowsApp.css';

const defaultPositionForLocationPicker = {
  lat: 43.4611843213236,
  lng: -3.8074053793457097
};


const ReduxNodePropertyInput = ({nodeElementId,activityId,elementIndex,properties,
      // functions
      changeProperty,setHelp
    }) => {
      var element=properties[elementIndex];
      console.log("element.options");
      console.log(element.name);
      console.log(element.type);
      console.log(element.options);
      console.log(element.value);

      var propertyInput=
          <div className="PropertiesField" key={elementIndex} >
            <span className="PropertiesLabel">{element.name}</span>
            <span className="PropertiesInput">
                { element.type == 'SELECT' &&
                      <select value={element.value} key={elementIndex} onChange={(event) => changeProperty(activityId,nodeElementId,elementIndex,event.target.value)}>
                      {element.options.map((option,i) =>
                        <option value={option.value} key={i}>{option.key}</option>
                      )}
                      </select>
                }
                { element.type == 'CHECKBOX' &&
                      <input key={elementIndex} name={"property"+elementIndex}
                        type="checkbox"
                        onClick={(event) => changeProperty(activityId,nodeElementId,elementIndex,event.target.checked.toString()) }
                        onFocus={(event) => setHelp(element.html,element.hint)}
                        checked={element.value == "true"}
                        />
                }
                { element.type == 'LOCATION_PICKER' &&
                      <div onFocus={(event) => setHelp(element.html,element.hint)}>
                        <div className="PropertiesSubLabel">
                          <span className="PropertiesLabel">latitude</span>
                          <input key={"latitude"} name={"latitude"}
                          type="text"
                          onChange={(event) => changeProperty(activityId,nodeElementId,elementIndex,event.target.value+','+element.value.split(",")[1]+','+element.value.split(",")[2]) }
                          value={Number(element.value.split(",")[0])}
                          />
                        </div>
                        <div>
                          <span className="PropertiesLabel">longitude</span>
                          <input key={"longitude"} name={"longitude"}
                          type="text"
                          onChange={(event) => changeProperty(activityId,nodeElementId,elementIndex,element.value.split(",")[0]+','+event.target.value+','+element.value.split(",")[2]) }
                          value={Number(element.value.split(",")[1])}
                          />
                        </div>
                        <div>
                        <span className="PropertiesLabel">radius</span>
                        <input key={"radius"} name={"radius"}
                        type="text"
                        onChange={(event) => changeProperty(activityId,nodeElementId,elementIndex,element.value.split(",")[0]+','+element.value.split(",")[1]+','+event.target.value) }
                        value={Number(element.value.split(",")[2])}
                        />
                        </div>
                        <LocationPicker
                          containerElement={ <div style={ {height: '100%'} } /> }
                          mapElement={ <div style={ {height: '150px'} } /> }
                          defaultPosition={{ lat: Number(element.value.split(",")[0]), lng: Number(element.value.split(",")[1]) }}
                          radius={Number(element.value.split(",")[2])}
                          onChange={({ position, address }) => { changeProperty(activityId,nodeElementId,elementIndex,position['lat'].toString()+','+position['lng'].toString()+','+element.value.split(",")[2])} }
                          zoom={12}
                        />
                      </div>
                }
                { element.type == 'SEPARATOR' &&
                      <hr/>
                }
                { (element.type == 'TEXT' || element.type == 'COLOR') &&
                      <input key={elementIndex} name={"property"+elementIndex}
                        type="text"
                        onChange={(event) => changeProperty(activityId,nodeElementId,elementIndex,event.target.value) }
                        onFocus={(event) => setHelp(element.html,element.hint)}
                        value={element.value}
                        />
                }
            </span>
            </div>
      return propertyInput;
}


export default ReduxNodePropertyInput
