@echo off
setlocal

echo 正在启动学生竞赛系统...
echo.

REM 检查是否存在target\classes目录，如果不存在则编译
if not exist target\classes (
    echo 未检测到编译后的类文件，正在编译项目...
    echo.
    call mvn clean compile
    
    if %errorlevel% neq 0 (
        echo 编译失败，请检查代码是否有错误！
        pause
        exit /b 1
    )
    echo.
    echo 编译成功！
echo.
)

REM 运行程序
java -cp target\classes com.example.studentcompetition.M1

endlocal