const fetchUser = async () => {
  try {
    const res = await fetch("http://localhost:5002/users", {
      method: "GET",
    });
    if (res.status == 200) {
      const user = await res.json();
      return user;
    }
  } catch (err) {
    console.error(err);
  }
};
const fetchUserById = async (id) => {
  try {
    const res = await fetch(`http://localhost:5002/users/${id}`, {
      method: "GET",
    });
    if (res.status == 200) {
      const user = await res.json();
      return user;
    }
  } catch (err) {
    console.error(err);
  }
}

const fetchFlower = async () => {
  try {
    const res = await fetch("http://localhost:5002/flowers", {
      method: "GET",
    });
    if (res.status == 200) {
      const flower = await res.json();
      return flower
    }
  } catch (err) {
    console.error(err);
  }
}

const fetchVegie = async () => {
  try {
    const res = await fetch("http://localhost:5002/vegies", {
      method: "GET",
    });
    if (res.status == 200) {
      const vegie = await res.json();
      return vegie
    }
  } catch (err) {
    console.error(err);
  }
}

const fetchFruit = async () => {
  try {
    const res = await fetch("http://localhost:5002/fruits", {
      method: "GET",
    });
    if (res.status == 200) {
      const fruit = await res.json();
      return fruit
    }
  } catch (err) {
    console.error(err);
  }
}


export { fetchUser, fetchFlower, fetchVegie, fetchFruit, fetchUserById };