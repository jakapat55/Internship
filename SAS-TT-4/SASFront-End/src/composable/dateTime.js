let clientTimeZone = 'UTC'
function getClientTimeZone(){
  clientTimeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  return clientTimeZone 
}
function getDateTime(dateTime){
  getClientTimeZone()
  let localDateTime
  if(dateTime === null){
    localDateTime = '-'
    return localDateTime
  }else{
    const utcDate = new Date(dateTime);
    const options = {
        timeZone: clientTimeZone,
        day: 'numeric',
        month: 'short',
        year: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
      };
  localDateTime = utcDate.toLocaleString('en-GB', options).replace(' at ', ', ');
    return localDateTime
}
}

function editDateTime(dateTime) {
  getClientTimeZone()
  let localDateTime;

  if (dateTime === null) {
    localDateTime = ['-','-'];
    return localDateTime;
  } else {
    const utcDate = new Date(dateTime);
    const options = {
      timeZone: clientTimeZone,
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
    };
    const formattedDateTime = utcDate.toLocaleString('en-GB', options).replace(/[/,-]/g, '-');
    const [date, time] = formattedDateTime.split(' ');
    localDateTime = [date.slice(0,-1), time];
    let y = localDateTime[0].slice(6)
    let m = localDateTime[0].slice(3)
    m = m.slice(0,-5)
    let d = localDateTime[0].slice(0,-8)
    localDateTime[0] = `${y}-${m}-${d}`
    return localDateTime;
  }
}
export {getDateTime,getClientTimeZone,editDateTime}