$prompt = "Generate a test suite for the following requirement: create test case for pen. Output MUST be valid JSON matching this schema: { ""test_suite_name"": ""string"", ""cases"": [ { ""id"": ""string"", ""description"": ""string"", ""steps"": [""string""], ""expected"": ""string"" } ] }. IMPORTANT: Use the exact key 'expected' for the expected result. Do NOT use 'expected_result' or 'result'."

$body = @{
    model  = "gemma3:1b"
    prompt = $prompt
    stream = $false
    format = "json"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:11434/api/generate" -Method Post -Body $body -ContentType "application/json" | Select-Object -ExpandProperty response
