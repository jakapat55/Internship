const deleteAnnouncement = async (Id)=>{
  let url = import.meta.env.VITE_ROOT_API
  let LINK = `${url}/api/announcements/${Id}`
   try{
      const res = await fetch(LINK,{
       method:'DELETE'
     })
     if(res.ok){
      location.reload()
       return res.ok
     }else {
      
      const errorData = await res.json();
      return errorData
    }}
   catch (err){
    console.log(err)
    throw new Error(errorData.message);
  }}
export { deleteAnnouncement }