import { Navigate, useRoutes } from 'react-router-dom';
import MeetingCreatePage from 'pages/MeetingCreatePage';
import MeetingListPage from 'pages/MeetingListPage';
import MeetingPage from 'pages/MeetingPage';
import RegisterPage from 'pages/RegisterPage';
import LoginPage from 'pages/LoginPage';
import Auth from './Auth';

const Router = () => {
  return useRoutes([
    { path: '/', element: <Navigate to="/meeting" replace /> },
    {
      element: <Auth isLoggedIn={false} />,
      children: [
        { path: '/login', element: <LoginPage /> },
        { path: '/register', element: <RegisterPage /> },
      ],
    },
    {
      element: <Auth isLoggedIn={true} />,
      children: [
        {
          path: '/meeting',
          children: [
            { path: '', element: <MeetingListPage /> },
            { path: ':id', element: <MeetingPage /> },
            { path: 'create', element: <MeetingCreatePage /> },
            { path: '*', element: <div>에러페이지</div> },
          ],
        },
      ],
    },
    { path: '*', element: <div>에러페이지</div> },
  ]);
};

export default Router;
