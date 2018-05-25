import React from 'react';
import {FormGroup, InputGroup, Button, FormControl} from "react-bootstrap";

const ChatInput = (props) => {
  return (
    <FormGroup>
      <InputGroup>
        <InputGroup.Button>
          <Button onClick={props.onSend} className="btn-outline-success">Send</Button>
        </InputGroup.Button>
        <FormControl onChange={props.onInputChange}
                     value={props.inputValue}
                     type="text"/>
      </InputGroup>
    </FormGroup>
  )
};

export default ChatInput;