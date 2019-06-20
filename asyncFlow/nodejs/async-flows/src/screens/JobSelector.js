import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import removeIcon from '../images/ic_remove_black_24dp_1x.png'
import AreYouSureDialog from '../screens/AreYouSureDialog';


class JobSelector extends Component {
	constructor(props) {
		super(props);
		this.state = {
			removeJobDialogOpened:false
		}
	}
	openRemoveJobDialog(id,name) {
		this.currentJobName=name;
		this.currentJobId=id;
		this.setState({
			removeJobDialogOpened:true
		});
	}
	closeRemoveJobDialog() {
		this.setState({
			removeJobDialogOpened:false
		});

	}
	removeJob(id,name)  {
		this.props.removeJob(id,name);
	}
	render(){
			var styleRect={opacity:0,fill:"#4387fd"}
		return (
<div className="JobSelectorPicker" tabIndex="0" >
	<div className="JobSelectorHeader">

			<div className="JobSelectorTitle" >Select a Job</div>
			<div className="CloseIcon" onMouseDown={(event) =>this.props.closeJobSelector()}>
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
			{this.props.jobs.map((jobItem)=>
					<div className="JobSelectorContentCell" key={jobItem.id} >
					<span>
					<button type="button" title="Remove Job" disabled={false} onClick={(event) => this.openRemoveJobDialog(jobItem.id,jobItem.name)}><img src={removeIcon} alt="Remove Job"/></button>
					</span>
						<span onMouseDown={(event)=>this.props.openJob(jobItem.id)} >{jobItem.name}</span>
					</div>)}
		</div>
	</div>
</div>

{this.state.removeJobDialogOpened  &&
<AreYouSureDialog label={"Are you sure you want to remove Job:"+this.currentJobName} answerYes={()=>this.removeJob(this.currentJobId,this.currentJobName)} closeDialog={()=>this.closeRemoveJobDialog()} />
}

</div>
	)
	}
}

export default JobSelector;
