import React, {Component} from 'react';
import axios from 'axios';
import logo from './logo.svg';
import './App.css';

import ChatInput from './input/ChatInput';
import AuthorInput from './author/AuthorInput';
import ChatHistory from './history/ChatHistory';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      chatLines: [],
      input: "",
      author: "",
    };

    this.onSend = this.onSend.bind(this);
    this.onInputChange = this.onInputChange.bind(this);
    this.onAuthorSubmit = this.onAuthorSubmit.bind(this);
  }

  componentDidMount() {
    axios.get('/api/chat')
      .then(response => {
        this.setState({chatLines: response.data})
      });
  }

  onSend(event) {
    event.preventDefault();

    const chatLine = {
      keyword: this.state.input,
      author: this.state.author
    };

    axios.post('/api/chat', chatLine)
      .then(response => {
        this.setState({
          input: "",
          chatLines: [response.data].concat(this.state.chatLines)
        });
      });
  }

  onInputChange(event) {
    this.setState({input: event.target.value});
  }

  onAuthorSubmit(event) {
    event.preventDefault();
    this.setState({author: event.target.name.value});
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
          {!this.state.author &&
          <AuthorInput onSubmit={this.onAuthorSubmit}/>
          }

          {this.state.author &&
          <ChatInput onSend={this.onSend}
                     onInputChange={this.onInputChange}
                     inputValue={this.state.input}
          />}

          {this.state.author &&
          <ChatHistory chatLines={this.state.chatLines}/>
          }
        </div>
      </div>
    );
  }
}

export default App;
