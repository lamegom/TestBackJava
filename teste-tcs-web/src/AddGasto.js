import React from 'react';
import { Row, Form, Col, Button } from 'react-bootstrap';
import CurrencyInput from 'react-currency-masked-input'
import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import momentLocalizer from "react-widgets-moment"
import moment from 'moment';
// Add the css styles...
import 'react-widgets/dist/css/react-widgets.css';
import AsyncSelect from 'react-select/async';

import $ from 'jquery';

let cat


class AddGasto extends React.Component {
  constructor(props) {
    super(props);
    this.initialState = {
      id: '',
      fromDate: props.fromDate || new Date().toISOString(),
      categoria:props.categoria
    }


    momentLocalizer(moment);
    if(props.gasto){
      this.state = props.gasto
    } else {
      this.state = this.initialState;
    }

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  getModelsAPI = (input) => {

    const url = 'http://localhost:8080/api/categorias/all';

    return fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            const formatted = json.map((l) => {
                return Object.assign({}, {
                    label: l.nome,
                    value: l.id
                });
            })
            console.log('formatted', formatted)
            return formatted
        })
}
  

  reset() {
    this.setState(this.initialState);
}

fromDateHandleChange = date => {
    this.setState({
      fromDate: date
    });

  };

  handleChange(event) {
    const name = event.target.name;
    const value = event.target.value;

    this.setState({
      [name]: value
    })

  }

  handleSelectChange(event) {

    const value = event.value;

    cat = value;

    

  }

  setCat(value){

    this.setState({
      categoria: value
    })
  }


  handleSubmit(event) {
    event.preventDefault();
    console.log('this.state.categoria: ', this.state.categoria)
    console.log('this.state.cat: ', this.state.cat)
    console.log('cat: ', cat)
    this.state.categoria = cat
    console.log('this.state: ', this.state)
    this.props.onFormSubmit(this.state);
    this.setState(this.initialState);
    setInterval(null, 5000);

  }


  render() {

    let pageTitle;

    if(this.state.id) {
      pageTitle = <h2>Edit Gasto</h2>
    } else {
      pageTitle = <h2>Add Gasto</h2>
      
    }

    return(
      <div>
        {pageTitle}
        <Row>
          <Col sm={6}>
            <Form onSubmit={this.handleSubmit} id="form1">
            <Form.Group controlId="descricao">
                <Form.Label>descrição</Form.Label>
                <Form.Control
                  type="text"
                  name="descricao"
                  value={this.state.descricao}
                  onChange={this.handleChange}
                  />
              </Form.Group>
              <Form.Group controlId="cat">

                <Form.Control
                  type="hidden"
                  name="categoria"
                  value={this.state.cat}
                  onChange={this.handleChange}
                  />
              </Form.Group>
              <Form.Group controlId="valor">
                <Form.Label>valor</Form.Label>
                <br/>
                  <CurrencyInput 
                    name="Valor"
                    value={this.state.valor} 
                    onChange={this.handleChange}
                     />
              </Form.Group>
              <Form.Group controlId="data">
                <Form.Label>data</Form.Label>
                <DateTimePicker
              id="from-date-dt"
              value={this.state.data}
              onChange={this.fromDateHandleChange}
              format='DD/MM/YYYY HH:mm:ss'
            />
              </Form.Group>
              
              <Form.Group controlId="cate">
                <Form.Label>categoria</Form.Label>
                <AsyncSelect cacheOptions defaultOptions 
                value={this.state.cat}
                loadOptions={this.getModelsAPI}
                onChange={this.handleSelectChange} 

                />

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

export default AddGasto;