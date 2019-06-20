import React, { Component } from 'react';
import '../AsyncFlowsApp.css';


import addIcon from '../images/ic_add_black_24dp_1x.png'

import backupIcon from '../images/ic_backup_black_24dp_1x.png'

import createIcon from '../images/ic_create_black_24dp_1x.png'
import saveIcon from '../images/ic_save_black_24dp_1x.png'
import deleteIcon from '../images/ic_delete_black_24dp_1x.png'

import clearIcon from '../images/ic_clear_black_24dp_1x.png'
import buildIcon from '../images/ic_build_black_24dp_1x.png'
import checkIcon from '../images/ic_check_circle_black_24dp_1x.png'



import playIcon from '../images/ic_play_arrow_black_24dp_1x.png'
import pauseIcon from '../images/ic_pause_black_24dp_1x.png'
import stopIcon from '../images/ic_stop_black_24dp_1x.png'

import settingsIcon from '../images/ic_settings_black_24dp_1x.png'

import printIcon from '../images/ic_print_black_24dp_1x.png'

import descriptionIcon from '../images/ic_description_black_24dp_1x.png'
import helpIcon from '../images/ic_help_black_24dp_1x.png'
import clearScreenIcon from '../images/ic_remove_circle_outline_black_24dp_1x.png'
import playJobIcon from '../images/ic_subscriptions_black_24dp_1x.png'
import stopJobIcon from '../images/ic_stop_black_24dp_1x.png'

import openJobIcon from '../images/ic_folder_open_black_24dp_1x.png'

import refreshIcon from '../images/ic_refresh_black_24dp_1x.png'
import removeIcon from '../images/ic_remove_black_24dp_1x.png'

import showProcessesIcon from '../images/ic_playlist_play_black_24dp_1x.png'
import showChartIcon from '../images/ic_show_chart_black_24dp_1x.png'

class ToolBarContainer extends Component {
	onClick() {
		alert("Not implemented yet");
	}

  render() {
    return (
      <div className="ToolBarContainer">


<button type="button" title="Add Activity" disabled={false} onClick={(event) => this.props.openNewActivityDialog()}><img alt="Add Activity"  src={addIcon}/></button>
<button type="button" title="Remove Activity" disabled={false} onClick={(event) => this.props.openRemoveActivityDialog()}><img alt="Remove Activity" src={removeIcon}/></button>
<button type="button" title="Open Job" disabled={false} onClick={(event)=> this.props.prepareOpenJobSelector()}><img alt="Open Job" src={openJobIcon}/></button>

{/*
<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={backupIcon}/></button>

<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={createIcon}/></button>
*/}

<button type="button" title="Save current Job (All Activities)" disabled={false} onClick={(event)=> this.props.saveJob()}><img src={saveIcon} alt="Save current Job (All Activities)" /></button>
<button type="button" title="Delete selected Element" onClick={(event) => this.props.deleteElement()}><img alt="Delete selected Element" src={deleteIcon}/></button>

<button type="button" title="Refresh Output" disabled={false} onClick={(event) => this.props.refreshOutput()}><img alt="Refresh Output" src={refreshIcon}/></button>
<button type="button" title="Clear Output" disabled={false} onClick={(event) => this.props.clearOutput()}><img alt="Clear Output" src={clearIcon}/></button>
{/*
<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={buildIcon}/></button>
<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={checkIcon}/></button>
*/}


<button type="button" title="Run Activity" disabled={false} onClick={(event) => this.props.runActivity()}><img alt="Run Activity" src={playIcon}/></button>
<button type="button" title="Show Chart" disabled={false} onClick={(event) => this.props.showLastChart()}><img alt="Show Last Chart" src={showChartIcon}/></button>
{/*
<button type="button" title="Not Implemented yet" disabled={true} onClick={this.onClick}><img src={pauseIcon}/></button>
*/}
<button type="button" title="Stop Activity" disabled={false} onClick={(event) => this.props.stopActivity()}><img src={stopIcon} alt="Stop Activity" /></button>

<button type="button" title="Run Job" disabled={false} onClick={(event) => this.props.runJob()}><img alt="Run Job" src={playJobIcon}/></button>
{/*
<button type="button" title="Not Implemented Yet" disabled={true} onClick={this.onClick}><img src={pauseIcon}/></button>
*/}
<button type="button" title="Stop Job" disabled={false} onClick={(event) => this.props.stopJob()}><img alt="Stop Job" src={stopJobIcon}/></button>



<button type="button" title="Clear Output" disabled={false} onClick={(event) => this.props.clearOutput()}><img src={clearScreenIcon} alt="Clear Output" /></button>

<button type="button" title="Show Processes" disabled={false} onClick={(event) => this.props.showProcesses()}><img src={showProcessesIcon} alt="Show Processes" /></button>
{/*
<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={settingsIcon}/></button>

<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={printIcon}/></button>

<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={descriptionIcon}/></button>
<button type="button" title="Not implemented yet" disabled={true} onClick={this.onClick}><img src={helpIcon}/></button>
*/}


      </div>
    );
  }
}

export default ToolBarContainer;
