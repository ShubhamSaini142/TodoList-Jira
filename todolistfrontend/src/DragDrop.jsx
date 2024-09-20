import React, { useState, useEffect } from 'react';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import axios from 'axios';
import './index.css';  

const DragDrop = () => {
  const [tasks, setTasks] = useState({
    todo: [],
    inProgress: [],
    underReview: [],
    completed: [],  
  });

  
  useEffect(() => {
    const fetchTasks = async () => {
      const result = await axios.get('http://localhost:8080/tasks/getAll');
    // console.log(result.data)
      setTasks({
        todo: result.data.filter(task => task.taskStatus === 'TODO'),
        inProgress: result.data.filter(task => task.taskStatus === 'INPROGRESS'),
        underReview: result.data.filter(task => task.taskStatus === 'UNDERREVIEW'),
        completed: result.data.filter(task => task.taskStatus === 'COMPLETED'),
      });
    };
    fetchTasks();
    
    
    const intervalId = setInterval(fetchTasks, 2000);
    return () => clearInterval(intervalId);
  }, []);

  const handleDragEnd = async (result) => {
    const { source, destination } = result;
    if (!destination) return;
  
    const sourceColumn = [...tasks[source.droppableId]];
    const destColumn = [...tasks[destination.droppableId]];
  
    console.log('Source Column:', sourceColumn); // Debugging source column
    console.log('Destination Column:', destColumn); // Debugging destination column
  
    if (!sourceColumn || !destColumn) {
      console.error('Invalid droppableId:', source.droppableId, destination.droppableId);
      return;
    }
  
    const [movedTask] = sourceColumn.splice(source.index, 1);
    destColumn.splice(destination.index, 0, movedTask);
  
    console.log('Updated Source Column:', sourceColumn); // Debugging updated source column
    console.log('Updated Destination Column:', destColumn); // Debugging updated destination column
  
    setTasks({
      ...tasks,
      [source.droppableId]: sourceColumn,
      [destination.droppableId]: destColumn,
    });
  
    try {
      await axios.post(`http://localhost:8080/tasks/update/${movedTask.taskid}`, {
        taskStatus: destination.droppableId.toUpperCase(),
      });
    } catch (error) {
      console.error('Failed to update task status', error);
      console.log('Source:', source.droppableId);
      console.log('Destination:', destination.droppableId);
    }
  };

  return (
    <DragDropContext onDragEnd={handleDragEnd}>
    <div className="kanban-board">
      {Object.entries(tasks).map(([columnId, columnTasks]) => (
        <Droppable droppableId={columnId} key={columnId}>
          {(provided) => (
            <div
              className="kanban-column"
              {...provided.droppableProps}
              ref={provided.innerRef}
            >
              <h3>{columnId.toUpperCase().replace('_', ' ')}</h3>
              {columnTasks.map((task, index) => (
                <Draggable key={task.taskid || index} draggableId={task.taskid ? task.taskid.toString() : index.toString()} index={index}>
                  {(provided) => (
                    <div
                      className="kanban-task"
                      {...provided.draggableProps}
                      {...provided.dragHandleProps}
                      ref={provided.innerRef}
                    >
                      <b><p>{task.taskName}</p></b>
                      <p>{task.taskDetails}</p>
                    </div>
                  )}
                </Draggable>
              ))}
              {provided.placeholder}
            </div>
          )}
        </Droppable>
      ))}
    </div>
  </DragDropContext>

  );
};

export default DragDrop;
    