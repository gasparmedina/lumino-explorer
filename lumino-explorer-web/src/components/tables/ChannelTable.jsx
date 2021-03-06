import React, {Component} from "react";
import 'react-table/react-table.css'
import ReactTable from 'react-table'


export default class ChannelTable extends Component {
    render =()=> {
        console.log(this.props);
        return <ReactTable
            data={this.props.data}
            columns={this.props.columns}
            defaultPageSize={this.props.defaultPageSize}
            showPagination={this.props.showPagination}
            sortable={this.props.sortable}
            showPageSizeOptions={this.props.showPageSizeOptions}
        />
    }
}
