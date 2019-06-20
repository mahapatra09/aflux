import React from 'react';
import ReactDOM from 'react-dom';
import AsyncFlowsApp from './AsyncFlowsApp';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
});
