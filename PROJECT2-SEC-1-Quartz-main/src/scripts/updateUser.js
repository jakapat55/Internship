const updateUser = async (id, objData) => {
  try {
    const res = await fetch(`http://localhost:5002/users/${id}`, {
      method: "PUT",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(objData),
    });
  } catch (err) {
    console.error(err);
  }
};
export { updateUser };
