var client = require('../functions/client');

import { CLOSE_PLUGIN_UPLOADER_DIALOG } from '../redux-actions/workspaceActionTypes';
import { openPluginManagerAction } from '../redux-actions/workspaceActionTypes';
import { applyGeneralActionAction } from '../redux-actions/aFluxActionTypes';




export function closePluginUploaderDialog() {
  window.reduxStore.dispatch({type:CLOSE_PLUGIN_UPLOADER_DIALOG});
};

export function prepareOpenPluginManager(host) {
    client({method: 'GET',
      mode:'no-cors',
      path: host+'/plugins'}).done(
        response => {openPluginManager(response.entity)});
};

function openPluginManager(pluginList) {
  var newPluginList=[];
  for (var i=0;i<pluginList.length;i++) {
    newPluginList.push(pluginList[i]);
  }
  window.reduxStore.dispatch(openPluginManagerAction(newPluginList));
};

export function redirectToShowPluginManagerPopup(host) {
  closePluginUploaderDialog();
  prepareOpenPluginManager(host);
};

export function closePluginManager() {
  window.reduxStore.dispatch(applyGeneralActionAction({
    pluginManagerPopupOpened:false
  },"closePluginManager"));
};
