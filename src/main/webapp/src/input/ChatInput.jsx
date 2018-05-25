import React from 'react';
import {Form, FormGroup, InputGroup, Button, FormControl} from "react-bootstrap";

const ChatInput = (props) => {
  return (
    <Form onSubmit={props.onSend}>
      <FormGroup>
        <InputGroup>
          <InputGroup.Button>
            <Button type="submit" className="btn-outline-success">Send</Button>
          </InputGroup.Button>
          <FormControl onChange={props.onInputChange}
                       value={props.inputValue}
                       type="text"/>
        </InputGroup>
      </FormGroup>
    </Form>
  )
};

export default ChatInput;