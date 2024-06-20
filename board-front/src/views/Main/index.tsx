import React, { useEffect, useState } from 'react';
import './style.css';
import Top3Item from 'components/Top3Item';
import { BoardListItem } from 'types/interface';
import { useNavigate } from 'react-router-dom';
import { SEARCH_PATH } from 'constant';
import { getLatestBoardListRequest, getPopularListRequest, getTop3BoardListRequest } from 'apis';
import { GetLatestBoardListResponseDTO, GetTop3BoardListResponseDTO } from 'apis/response/board';
import { ResponseDto } from 'apis/response';
import { usePagination } from 'hooks';
import Pagination from 'components/Pagination';
import BoardItem from 'components/BoardItem';
import { GetPopularListResponseDTO } from 'apis/response/search';

export default function Main() {
  const navigate = useNavigate();

  const MainTop = () => {
    const [top3BoardList, setTop3BoardList] = useState<BoardListItem[]>([]);

    const getTop3BoardListResponse = (responseBody: GetTop3BoardListResponseDTO | ResponseDto | null) => {
      if (!responseBody) return;
      const { code } = responseBody;
      if (code === 'DBE') alert('데이터베이스 오류입니다.');
      if (code !== 'SU') return;

      const { top3List } = responseBody as GetTop3BoardListResponseDTO;
      setTop3BoardList(top3List || []);
    };

    useEffect(() => {
      getTop3BoardListRequest().then(getTop3BoardListResponse);
    }, []);

    return (
      <div id='main-top-wrapper'>
        <div className='main-top-container'>
          <div className='main-top-title'>{'HM board에서\n다양한 이야기를 나눠보세요'}</div>
          <div className='main-top-contents-box'>
            <div className='main-top-contents-title'>{'주간 TOP 3 게시글'}</div>
            <div className='main-top-contents'>
              {top3BoardList.map(top3ListItem => (
                <Top3Item key={top3ListItem.boardNumber} top3ListItem={top3ListItem} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  };
  //          component: 메인 화면 하단 컴포넌트           //
  const MainBottom = () => {
    //        state: 페이지네이션 관련 상태           //
    const {
      currentPage, setCurrentPage, currentSection, setCurrentSection, viewList,
      viewPageList, totalSection, setTotalList, totalList
    } = usePagination<BoardListItem>(5);
    //        state: 인기 검색어 리스트 상태           //
    const [popularWordList, setPopularWordList] = useState<string[]>([]);

    //       function: get latest board list response 처리 함수       //
    const getLatestBoardListResponse = (responseBody: GetLatestBoardListResponseDTO | ResponseDto | null) => {
      console.log('Response Body:', responseBody); // responseBody 로그 추가
      if (!responseBody) return;
      const { code,message, latestList } = responseBody as GetLatestBoardListResponseDTO;
      console.log('Code:', code); // 코드 로그 추가
      console.log('Message:', message); // 메시지 로그 추가
      if (code === 'DBE') alert('데이터베이스 오류입니다.');
      if (code !== 'SU') return;

      console.log('Latest List:', latestList); // latestList 로그 추가
      setTotalList(latestList || []);
    };
    //       function: getPopularListResponse 처리 함수       //
    const getPopularListResponse = (responseBody: GetPopularListResponseDTO | ResponseDto | null) => {
      if (!responseBody) return;
      const { code } = responseBody;
      if (code === 'DBE') alert('데이터베이스 오류입니다.');
      if (code !== 'SU') return;

      const { popularWordList } = responseBody as GetPopularListResponseDTO;
      setPopularWordList(popularWordList);
    };

    const onPopularWordClickHandler = (word: string) => {
      navigate(SEARCH_PATH(word));
    };

    useEffect(() => {
      getLatestBoardListRequest().then(response => {
        getLatestBoardListResponse(response);
      });
      getPopularListRequest().then(getPopularListResponse);
    }, []);

    useEffect(() => {
      console.log('Total List:', totalList); // totalList 로그 추가
      console.log('View List:', viewList); // viewList 로그 추가
    }, [totalList, viewList]);

    return (
      <div id='main-bottom-wrapper'>
        <div className='main-bottom-container'>
          <div className='main-bottom-title'>{'최신 게시물'}</div>
          <div className='main-bottom-contents-box'>
            <div className='main-bottom-current-contents'>
              {viewList.map(boardListItem => (
                <BoardItem key={boardListItem.boardNumber} boardListItem={boardListItem} />
              ))}
            </div>
            <div className='main-bottom-popular-box'>
              <div className='main-bottom-popular-card'>
                <div className='main-bottom-popular-card-container'>
                  <div className='main-bottom-popular-card-title'>{'인기 검색어'}</div>
                  <div className='main-bottom-popular-card-contents'>
                    {popularWordList.map(word => (
                      <div key={word} className='word-badge' onClick={() => onPopularWordClickHandler(word)}>{word}</div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className='main-bottom-pagination-box'></div>
          <Pagination
            currentPage={currentPage}
            currentSection={currentSection}
            setCurrentPage={setCurrentPage}
            setCurrentSection={setCurrentSection}
            viewPageList={viewPageList}
            totalSection={totalSection}
          />
        </div>
      </div>
    );
  };

  return (
    <>
      <MainTop />
      <MainBottom />
    </>
  );
}
