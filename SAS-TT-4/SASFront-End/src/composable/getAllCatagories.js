async function getAllCategoey(){
  let url = import.meta.env.VITE_ROOT_API 
  let LINK = `${url}/api/categories`
  try{
      const res = await fetch(LINK)
      if(res.ok){
        const allCategoey = res.json()
        return allCategoey
      }
      else {throw new error("We Could Not GET All Categories")}
    }
    catch (error){console.log(error)}
  }
  export { getAllCategoey }
  