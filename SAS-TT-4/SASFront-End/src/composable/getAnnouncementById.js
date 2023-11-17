async function getAnnouncementById(Id,count){
  let url = import.meta.env.VITE_ROOT_API
  let LINK = `${url}/api/announcements/${Id}?count=${count}`
  try{
   const res = await fetch(LINK)
   if(res.ok){
    const singleAnnouncement = res.json()
    return singleAnnouncement
  }
  else {
    throw new error(`We Could Not GET Announcemen By Id ${Id}`)
  }
  }
  catch (error){
    console.log(error)
    console.log('The request page is not available');
  }}
export { getAnnouncementById }