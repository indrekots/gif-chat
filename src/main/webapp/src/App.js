import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <h1 className="App-title">Welcome to gif chat</h1>
          <p className="App-intro">
            This is a chat app where you can only use gifs
          </p>
        </header>
      </div>
    );
  }
}

export default App;
