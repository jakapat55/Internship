# PROJECT1-SEC-1-Quartz

## MEMBER
__1. จักรพัชร	ผลบุญเจริญชัย		(64130500008) (20%)__
  > กด hint แล้วลบคะแนนตาม mode (ความยาก), ลดเวลาตาม mode (ความยาก), แก้ไขบัคหน้า ranking ตรงที่ คะแนนไม่มาทันที, เมื่อเวลาหมดให้ขึ้น pop-up แจ้งเตือน

__2. นันท์นภัส		เพชรราช		(64130500043) (20%)__
  > delete คะแนนของ users ในหน้า ranking, update คะแนนของ users ในหน้า ranking, ปุ่มข้ามข้อ, เปลี่ยนการแสดงคำถามจากทั้งหมดใน 1 หน้าเป็น 1 ข้อ ต่อ 1 หน้า

__3. พงศธร		จันทร์สงเคราะห์		(64130500051) (20%)__
  > ส่ง params จากหน้า mode ไปยังหน้า question เพื่อเช็คว่าต้อง fetch ข้อมูลใดมาแสดงเป็นคำถามให้ Player เล่นเกม

__4. ภัทรพล		แซ่ฟอง					(64130500064) (20%)__
  > ส่ง params จาก category ไปให้หน้า mode รวมไปถึงแก้ไข CSS ตาม Category เช่น พื้นหลังเปลี่ยนตามหมวดหมู่, แก้ไขและสร้าง database ให้ใช้งานได้ตามสมควร, ตรวจหาบัคและแก้ไข, แบ่ง components

__5. วรรณรัตน์	พุ่มพิพัฒน์				(64130500073) (20%)__
  > ทำหน้าสร้าง username เพื่อเข้าเล่นเกม โดยห้ามซ้ำกับชื่อที่เคยมีอยู่แล้ว, ให้ผู้เล่นสามารถแก้ไขชื่อตนเองได้พร้อมกับเปลี่ยนสีไอคอนหรือตัวละครที่แสดงในหน้า homepage, ช่วยเพื่อนดูเรื่องการเช็คว่าควร fetch ข้อมูลไหนมาในกรณีนี้เพื่อใช้ในการเล่นเกมตามหมวดหมู่และความยาก, ตรวจหาบัคและแก้ไข

<hr>

## FEATURES
- Redesign หน้า User Interfaces ของเกมจาก Feedback ของโปรเจคในรอบที่เเล้ว
- เพิ่ม Category อีก 2 category คือ 'Vegie'เเละ 'Flower'
- เพิ่มระดับความยากง่ายของเกม ประกอบด้วย 3 ระดับคือ 'Easy', 'Normal' เเละ 'Hard'
- คะแนนในแต่ละระดับจะแตกต่างกัน เช่น
  - **Easy** คะแนนข้อละ 1 คะแนน (คะแนนเต็ม 10 คะแนน)
  - **Normal** คะแนนข้อละ 2 คะแนน (คะแนนเต็ม 20 คะแนน)
  - **Hard** คะแนนข้อละ 3 คะแนน (คะแนนเต็ม 30 คะแนน)
- ในระหว่างที่เล่นเกม เมื่อผู้เล่นกดปุ่ม 'Hint' จะทำการลดคะแนนตามระดับที่เล่น /*(โดยคะแนนสามารถติดลบได้)*/ เช่น
  - **Easy** ลดทีละ 0.5 คะแนน
  -  **Normal** ลดทีละ 1 คะแนน
  -  **Hard** ลดทีละ 1.5 คะแนน
- เพิ่มในส่วนของปุ่มข้ามข้อ 
- เพิ่มในส่วนของ Ranking แสดงคะแนนที่แต่ละ Player เล่นได้
- เพิ่มในส่วนของการลดเวลาในการตอบคำถามตามระดับความยากง่ายที่เลือกไว้
- รองรับ **CRUD** ของเกม ได้แก่
  - **Create**; การสร้าง Player name,การสร้างคะแนนใน Ranking ตาม player name คนนั้น
  - **Read**; การแสดงข้อมูล Player name, ข้อมูลคะแนนในหน้า Ranking 
  - **Update**; การเปลี่ยนชื่อของ Player เเละเปลี่ยนสีของตัวโปรไฟล์  Player ได้
  - **Delete**; การลบข้อมูล Player name เเละการลบข้อมูลคะแนนน Ranking

<hr>

## MANUAL
**ส่วนที่ 1 หน้าลงทะเบียนผู้เล่น (Player register page)**
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/1.png">

- Player name' field; กรอก Player name เพื่อเข้าสู่หน้าเกม
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/name%20field.png">

- ปุ่ม Play; กดปุ่มเพื่อลงทะเบียน player name เพื่อเข้าสู่เกม
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/playBtn.png">

**ส่วนที่ 2 หน้าแรก (Homepage)**
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/2.png">

- ปุ่มแก้ไขข้อมูล Player; กดปุ่มเพื่อทำการแก้ไขข้อมูล Player เมื่อกดแล้วจะ redirect ไปที่หน้าแก้ไขข้อมูล user
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/2-1.png">

- ปุ่ม START; กดปุ่มนี้แล้วจะ redirect ไปยังหน้าเลือก category
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/2-2.png">

- ปุ่ม Ranking; กดปุ่มเพื่อแสดงรายชื่อ Player เเละ Score ของ Player คนนั้น
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/2-3.png">

- ปุ่ม TUTORIAL; กดปุ่มนี้เพื่อดูวิธีการเล่นเพิ่มเติม
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/2-4.png">

- ปุ่ม CREDITS; กดปุ่มนี้เพื่อดูแหล่งอ้างอิงรูปภาพและ icons
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/2-5.png">

**ส่วนที่ 3 หน้า Edit User**
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/EditUser.png">

  - ช่องเลือกสี; กดเพื่อเลือกสีของตัวละครตามที่ต้องการ
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/colorCharacter.png">

  - Edit player name's field; กรอกชื่อ player name ใหม่ตามที่ต้องการ
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/nameCharacter.png">
  
**ส่วนที่ 4 หน้าเลือก Category**  (โดยเมื่อเลือก category แล้ว เว็บจะ redirect ไปยังหน้าเลือก Mode)
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/3.png">

- ปุ่ม Fruit; กดปุ่มเพื่อเลือกคำถามหมวดผลไม้
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/3-1.png">

- ปุ่ม Vegie; กดปุ่มเพื่อเลือกคำถามหมวดผัก
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/3-2.png">

- ปุ่ม Flower; กดปุ่มเพื่อเลือกคำถามหมวดดอกไม้
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/3-3.png">

- ปุ่ม Home; กดปุ่มเพื่อกลับไปยังหน้า Homepage
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/3-4.png">
  
**ส่วนที่ 5 หน้าเลือก Mode** (โดยเมื่อเลือก mode แล้ว เว็บจะ redirect ไปยังหน้าเกม)
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/4.png">

- ปุ่ม Easy; กดปุ่มเพื่อเลือกระดับของคำถามเป็นระดับ 'ง่าย'
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/4-1.png">

- ปุ่ม Normal; กดปุ่มเพื่อเลือกระดับของคำถามเป็นระดับ 'ปานกลาง'
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/4-2.png">

- ปุ่ม Hard; กดปุ่มเพื่อเลือกระดับของคำถามเป็นระดับ 'ยาก'
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/4-3.png">

- ปุ่มย้อนกลับ; กดปุ่มเพื่อกลับไปยังหน้าก่อนหน้านี้
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/4-4.png">

- ปุ่ม Home; กลับไปยัง homepage
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/3-4.png">
  
**ส่วนที่ 6 ภายในเกม (in-game page)**
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/gameSection.png">
- พยางค์; บอกให้ผู้เล่นทราบว่าในข้อนั้นๆคำตอบมีทั้งหมดกี่พยางค์
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/pronounce.png">
- คะแนน; เมื่อผู้ใช้งานตอบถูก คะแนนจะเพิ่มขึ้นตามระดับความยากที่เลือกไว้ เช่น เริ่มแรกเป็น 0/10 ตอบถูก 1 ข้อ คะแนนจะเปลี่ยนเป็น 1/10 เมื่อตอบผิดจะไม่มีอะไรเปลี่ยนแปลง
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/point.png">
- โจทย์; อยู่ในรูปแบบของรูปภาพ ผู้เล่นจะต้องทายว่าแต่ละภาพสื่อถึงคำว่าอะไร จากนั้นจึงเอาคำทั้งหมดมารวมกันกลายเป็นคำตอบ
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/imgQuestion.png">
- ช่องใส่คำตอบและปุ่มตอบคำถาม (ANSWER); เมื่อผู้เล่นต้องการที่จะตอบให้คลิกที่ช่องใส่คำตอบจากนั้นจึงกดปุ่มส่งคำตอบหรือปุ่ม ANSWER ที่อยู่ข้างๆเพื่อเช็คว่าที่ท่านคิดถูกหรือไม่ โดยผู้เล่นไม่สามารถกดปุ่มได้หากยังไม่พิมพ์อะไรใดๆในช่องคำตอบและไม่สามารถใส่ช่องว่าง (blank space) ได้ เมื่อผู้เล่นตอบคำถามจะมีป็อปอัพขึ้นมา 2 กรณี ดังนี้
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/answer.png">
  - คำตอบถูก; ป็อปอัพแสดงว่าคำถามที่ท่านตอบมานั้นถูกต้องเป็นเวลา 1 วินาที
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/correct.png">
  - คำตอบผิด; ป็อปอัพแสดงว่าคำถามที่ท่านตอบมานั้นไม่ถูกต้องเป็นเวลา 1 วินาที
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/inCorrect.png">
- ปุ่ม Skip; กดปุ่มเพื่อข้ามคำถามในข้อปัจจุบันไปยังข้อถัดไป
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/skipBtn.png">
- การจับเวลา; ผู้เล่นมีเวลาเล่นทั้งหมด 5 นาที ต่อโจทย์ 10 ข้อ เมื่อเวลาหมดผู้เล่นจะต้องเริ่มเล่นใหม่ทั้งหมดโดยการกดปุ่ม START ที่หน้าแรก
  - เวลาเหลือมากกว่า 10 วินาที; ตัวเลขเป็นสีขาวอยู่กับที่
    <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/timer.png">
  - เวลาเหลือน้อยกว่าหรือเท่ากับ 10 วินาที; ตัวเลขกลายเป็นสีแดง พร้อมขยับซ้าย-ขวา
    <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/lessTimer.png">
- คำใบ้; หากท่านคิดไม่ออกว่าคำตอบของข้อนี้คืออะไรท่านสามารถกดปุ่มคำใบ้ (hint) ในข้อนั้นๆได้ โดยการกดคำใบ้ จะถูกลดคะแนนลงด้วย
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/hintBtn.png">
  - เมื่อกดคำใบ้; คำใบ้จะแสดงเป็นเวลา 5 วินาที
    <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/hintText.png">
- ป็อปอัพสรุปคะแนน; เมื่อท่านตอบคำถามในข้อสุดท้าย (ข้อที่ 10) ป็อปอัพการสรุปคะแนนจะขึ้นแสดงให้ท่านเห็นว่าท่านตอบถูกกี่ข้อ ตอบผิดกี่ข้อและได้คะแนนทั้งหมดเท่าไหร่ เมื่อกดปิดป็อบอัพ เว็บจะ redirect ไปยังหน้า ranking เพื่อแสดงให้เห็นว่าเรามีคะแนนอยู่ที่ลำดับเท่าไหร่
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/totalScore.png">
- ปุ่มโฮม; ท่านสามารถกดออกไปหน้าแรกเพื่อเริ่มเล่นใหม่หรือยุติการเล่นได้ทุกเมื่อที่ต้องการโดยการคลิกที่ปุ่มโฮม
  <img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/3-4.png">

**ส่วนที่ 7 หน้า Ranking**
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/ranking.png">

- Ranking Table; ตารางแสดงอันดับ ชื่อ เเละคะแนนที่ผู้เล่นทำคะแนนได้จากเกม
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/rowScore.png">

- ปุ่มถังขยะ; กดปุ่มเพื่อลบข้อมูล 'ชื่อ' เเละ 'คะแนน' ของผู้เล่นที่กดลบข้อมูล
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/deleteScore.png">

- ปุ่มย้อนกลับ; กดปุ่มเพื่อย้อนกลับไปยังหน้า Homepage
<img src="https://github.com/tarannaw/PROJECT2-SEC-1-Quartz/blob/main/guess-manual2/backBtn.png">
<hr>

## DEMO
> https://youtu.be/kCm7X3PeBRI?si=xykRcKvIv7C9WWZm

<hr>

## INSPIRATION
แรงบันดาลในการทำ web นี้เกิดขึ้นมาจากรายการ Davinci หรือ เกมถอดรหัส ที่เราจะให้รูปภาพมาแล้วให้ทายว่าจากรูปภาพที่ให้มาสามารถรวมเป็นคำอะไรได้บ้าง โดยที่กลุ่มของพวกเราได้เพื่มคำใบ้เพื่อให้ง่ายต่อการเล่น รวมไปถึงมีเวลาจำกัดในการเล่นเพื่อเพิ่มความสนุก ลุ้นละทึกให้กับเกมมากยิ่งขึ้น และยังได้นำ Feedback ของเพื่อนๆ และอาจารย์มาปรับปรุงและพัฒนาจนเกิด project ที่ 2 ขึ้น (มาพิสูจน์กันดีกว่าว่า... คุณมีไหวพริบปฏิภาณและความสามารถมากพอหรือไม่ที่จะเป็น “ดาวินชี่คนต่อไป”)
