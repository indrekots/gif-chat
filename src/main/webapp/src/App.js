import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

import ChatInput from './input/ChatInput';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      chatLines: [],
      input: "hello world yo"
    };

    this.onSend = this.onSend.bind(this);
    this.onInputChange = this.onInputChange.bind(this);
  }

  onSend() {
    this.setState({input: "hello world"}, () => console.log(this.state));
  }

  onInputChange(event) {
    console.log(event.target.value);
    this.setState({input: event.target.value});
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <h1 className="App-title">Welcome to gif chat</h1>
        </header>
        <p className="App-intro">
          This is a chat app where you can only use gifs
        </p>

        <div className="container">
          <ChatInput onSend={this.onSend}
                     onInputChange={this.onInputChange}
                     inputValue="Hello world fjaldfjlakj"
          />
        </div>
      </div>
    );
  }
}

export default App;
