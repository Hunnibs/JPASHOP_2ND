# JPASHOP_2ND 
### 선 코딩 후 강의 수강 

### Junit5 사용시 예외케이스 테스트
- 해당 코드를 사용하면 예외처리를 쉽게 해줄 수 있음

ex)
- assertThatThrownBy(() -> memberService.join(member2))
  .isInstanceOf(IllegalStateException.class);
- assertThrows(NotEnoughStockException.class, ()->{
  Long orderId = orderService.order(member.getId(), movie.getId(), 99);
  });
