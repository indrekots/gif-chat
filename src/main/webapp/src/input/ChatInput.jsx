import React from 'react';
import {FormGroup, InputGroup, Button, FormControl} from "react-bootstrap";

const ChatInput = (props) => {
  return (
    <FormGroup>
      <InputGroup>
        <InputGroup.Button>
          <Button onClick={props.onSend}>Send</Button>
        </InputGroup.Button>
        <FormControl onChange={props.onInputChange}
                     value={props.value}
                     type="text"/>
      </InputGroup>
    </FormGroup>
  )
};

export default ChatInput;