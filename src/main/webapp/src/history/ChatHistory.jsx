import React from 'react';
import {Grid, Row, Col} from 'react-bootstrap';

const ChatHistory = (props) => {
  return (
    <Grid>
      {props.chatLines.map((item, index) => (
        <Row key={index}>
          <Col md={3}>
            {item.author}
          </Col>
          <Col md={3}>
            {item.keyword}
          </Col>
        </Row>
      ))}
    </Grid>
  )
};

export default ChatHistory;