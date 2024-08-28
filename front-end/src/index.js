import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { Provider } from 'react-redux';
import {store} from "./redux/store";
import App from "./App.tsx";


const root = ReactDOM.createRoot(document.getElementById('container'));
root.render(
  <React.StrictMode>
      <Provider store={store}>
        <App />
      </Provider>
  </React.StrictMode>
);
