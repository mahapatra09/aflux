import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import removeIcon from '../images/ic_remove_black_24dp_1x.png'
import activateIcon from '../images/ic_thumb_up_black_24dp_1x.png'
import deactivateIcon from '../images/ic_thumb_down_black_24dp_1x.png'

import AreYouSureDialog from '../screens/AreYouSureDialog';


class PluginManagerPopup extends Component {
	constructor(props) {
		super(props);
		this.state = {
			removeDialogOpened:false
		}
	}
	openRemovePluginDialog(id,name) {
		this.setState({
			currentPluginId:id,
			currentPluginName:name,
			removeDialogOpened:true
		});
	}
	closeRemovePluginDialog() {
		this.setState({
			removeDialogOpened:false
		});

	}
	render(){
			var styleRect={opacity:0,fill:"#4387fd"}
		return (
<div className="PluginManagerPopup" tabIndex="0" >
	<div className="JosSelectorHeader">

			<div className="JobSelectorTitle" >Manage Plugins</div>
			<div className="CloseIcon" onMouseDown={(event) =>this.props.closePopup()}>
				<span className="toolTipClose">Close</span>
				<div role="button" className="JobSelecta-b-c" tabIndex="0" >
					<div className="Jm-vu-db-Oh">
					<svg width="25px" height="25px">
						<g ><polygon points="10.5,11.914 13.5,14.914 14.914,13.5 11.914,10.5 14.914,7.5 13.5,6.086 10.5,9.086 7.5,6.086 6.085,7.5 9.085,10.5 6.085,13.5 7.5,14.914 " id="polygon6" transform="translate(-2.9561186,-3.5084746)"/>
<rect width="21" height="21" id="rect8" x="0" y="-6" style={styleRect} />
</g>
					</svg>
				</div>
			</div>

	</div>
	<div className="JobSelectorMainContent">
		<div className="JobSelectorContentHeader">
			<div className="JobSelectorHeaderCell">
			Name
			</div>
		</div>
		<div className="JobSelectorContent" >
			{this.props.plugins.map((plugin)=>
					<div key={plugin.id} className="JobSelectorContentCell">
					<span>
					<button type="button" title="Remove Plugin" disabled={false} onClick={(event) => this.openRemovePluginDialog(plugin.id,plugin.name)}><img src={removeIcon} alt="Remove Plugin"/></button>
					{plugin.dynamicActivation && <button type="button" title="Activate" disabled={false} onClick={(event) => this.props.activatePlugin(plugin.id)}><img src={activateIcon} alt="Activate"/></button>
					}
					{plugin.activated && <button type="button" title="Deactivate" disabled={false} onClick={(event) => this.props.deactivatePlugin(plugin.id)}><img src={deactivateIcon} alt="Deactivate"/></button>}
					</span>
						<span>{plugin.jarLocation}</span>
						<span>{plugin.jarName}</span>
						<span>{plugin.activated?"(ACTIVE)":"(INACTIVE)"}</span>
					</div>)}
		</div>
	</div>
</div>

{this.state.removeDialogOpened  &&
<AreYouSureDialog label={"Are you sure you want to remove Plugin:"+this.state.currentPluginName} answerYes={()=>this.props.removePlugin(this.state.currentPluginId)} closeDialog={()=>this.closePLuginDialog()} />
}

</div>
	)
	}
}

export default PluginManagerPopup;
