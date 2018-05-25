import React from 'react';
import {
  Form, FormGroup, ControlLabel, FormControl, Button
} from "react-bootstrap";

const AuthorInput = (props) => {
  return (
    <Form inline onSubmit={props.onSubmit}>
      <FormGroup controlId="name">
        <ControlLabel>Enter your name</ControlLabel>
        <FormControl type="text" placeholder="Jane Doe"/>
      </FormGroup>
      <Button type="submit"
              className="btn-success">Start chatting with gifs</Button>
    </Form>
  )
};

export default AuthorInput;