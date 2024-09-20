import React from 'react'
import axios from 'axios';
import { useState } from 'react';
import DragDrop from './DragDrop';


const Homes = () => {
 const [formdata,setFormdata] = useState({
  taskName:'',
  taskDetails:'',
  taskStatus:'TODO'
 });

  const handleChange = (e) =>{
    const {name, value} = e.target;
    setFormdata({
      ...formdata,
      [name]:value
    })

  }

  const handleTaskCreation = async(e) =>{
    e.preventDefault();
    try{
      const response = await axios.post('http://localhost:8080/tasks/create',formdata);
      if(response.status === 200){
        alert("Task Created!");
        setFormdata({
          taskName: '',
          taskDetails: '',
          taskStatus: 'Todo', 
        });
        
      }
    }catch(error){
      console.error("Something went wrong")
    }
  }

  return (
    <div>
      <div className="">
        <form onSubmit={handleTaskCreation} method='POST'>
        <label>Enter the Taskname</label>
        <input  name="taskName" type= "text" value={formdata.taskName} onChange={handleChange}></input>
        <label>Enter the TaskDetails</label>
        <input  name="taskDetails" type= "text" value={formdata.taskDetails} onChange={handleChange}></input>
        <button type='submit'>Add Task</button>
       </form>
      </div>
      <div className="drag">

        <DragDrop/>
      </div>
        
    </div>
  )
}

export default Homes;