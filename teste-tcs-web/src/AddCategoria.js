import React from 'react';
import { Row, Form, Col, Button } from 'react-bootstrap';


class AddCategoria extends React.Component {
  constructor(props) {
    super(props);
    this.initialState = {
      id: '',
      nome: ''
    }

    if(props.categoria){
      this.state = props.categoria
    } else {
      this.state = this.initialState;
    }

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  reset() {
    this.setState(this.initialState);
}

  handleChange(event) {
    const name = event.target.name;
    const value = event.target.value;

    this.setState({
      [name]: value
    })
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.onFormSubmit(this.state);
    this.setState(this.initialState);
    this.reset();
    console.log('this.state.id: ',this.state.id);
  }

  render() {

    let pageTitle;

    if(this.state.id) {
      pageTitle = <h2>Edit Categoria</h2>
    } else {
      pageTitle = <h2>Add Categoria</h2>
    }

    return(
      <div>
        {pageTitle}
        <Row>
          <Col sm={6}>
            <Form onSubmit={this.handleSubmit}>
              <Form.Group controlId="categoriaName">
                <Form.Label>Product Name</Form.Label>
                <Form.Control
                  type="text"
                  name="nome"
                  value={this.state.nome}
                  onChange={this.handleChange}
                  placeholder="Nome"/>
              </Form.Group>
              <Form.Group>
                <Form.Control type="hidden" name="id" value={this.state.id} />
                <Button variant="success" type="submit">Save</Button>
              </Form.Group>
            </Form>
          </Col>
        </Row>
      </div>
    )
  }
}

export default AddCategoria;